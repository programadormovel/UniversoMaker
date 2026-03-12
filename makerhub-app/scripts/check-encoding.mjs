import fs from 'node:fs';
import path from 'node:path';

const root = process.cwd();
const utf8Decoder = new TextDecoder('utf-8', { fatal: true });

const textExtensions = new Set([
  '.ts',
  '.tsx',
  '.js',
  '.jsx',
  '.mjs',
  '.cjs',
  '.json',
  '.md',
  '.css',
  '.scss',
  '.html',
  '.xml',
  '.yml',
  '.yaml',
  '.txt',
  '.properties',
  '.gradle'
]);

const ignoredDirNames = new Set([
  '.git',
  '.gradle',
  'node_modules',
  'dist',
  'build',
  'coverage'
]);

const ignoredPathPrefixes = [
  'android/app/src/main/assets/public/assets/',
  'android/app/build/'
];

const BOM_UTF8 = [0xef, 0xbb, 0xbf];
const mojibakePattern = /Ã[\u0080-\u00BF]|Â[\u0080-\u00BF]|â[\u0080-\u00BF]{1,2}/;

const issues = [];

const normalize = (value) => value.split(path.sep).join('/');

const shouldIgnorePath = (relativePath) =>
  ignoredPathPrefixes.some((prefix) => relativePath.startsWith(prefix));

const listLineNumbers = (text, pattern, max = 6) => {
  const lines = text.split(/\r?\n/);
  const result = [];

  for (let index = 0; index < lines.length; index += 1) {
    if (pattern.test(lines[index])) {
      result.push(index + 1);
      if (result.length >= max) break;
    }
  }

  return result;
};

const analyzeFile = (absolutePath) => {
  const relativePath = normalize(path.relative(root, absolutePath));
  const extension = path.extname(absolutePath).toLowerCase();

  if (!textExtensions.has(extension)) return;
  if (shouldIgnorePath(relativePath)) return;

  const bytes = fs.readFileSync(absolutePath);

  if (
    bytes.length >= 3 &&
    bytes[0] === BOM_UTF8[0] &&
    bytes[1] === BOM_UTF8[1] &&
    bytes[2] === BOM_UTF8[2]
  ) {
    issues.push(`${relativePath}: UTF-8 BOM detected`);
  }

  let text;
  try {
    text = utf8Decoder.decode(bytes);
  } catch {
    issues.push(`${relativePath}: invalid UTF-8 byte sequence`);
    return;
  }

  if (text.includes('\uFFFD')) {
    const lines = listLineNumbers(text, /\uFFFD/g);
    issues.push(`${relativePath}: replacement character found (line(s): ${lines.join(', ')})`);
  }

  if (mojibakePattern.test(text)) {
    const lines = listLineNumbers(text, mojibakePattern);
    issues.push(`${relativePath}: possible mojibake sequence (line(s): ${lines.join(', ')})`);
  }
};

const walkDirectory = (directory) => {
  for (const entry of fs.readdirSync(directory, { withFileTypes: true })) {
    const absolutePath = path.join(directory, entry.name);
    const relativePath = normalize(path.relative(root, absolutePath));

    if (entry.isDirectory()) {
      if (ignoredDirNames.has(entry.name)) continue;
      if (shouldIgnorePath(`${relativePath}/`)) continue;
      walkDirectory(absolutePath);
      continue;
    }

    if (entry.isFile()) {
      analyzeFile(absolutePath);
    }
  }
};

walkDirectory(root);

if (issues.length > 0) {
  console.error('Encoding check failed:');
  for (const issue of issues) {
    console.error(`- ${issue}`);
  }
  process.exit(1);
}

console.log('Encoding check passed.');
