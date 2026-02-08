module.exports = {
  content: [
    './index.html',
    './src/**/*.{js,ts,jsx,tsx}'
  ],
  theme: {
    extend: {
      colors: {
        // Brand palette derived from provided images
        brand: {
          DEFAULT: '#083042', // dark navy/teal for primary text/header
          50: '#f3f7f8',
          100: '#e6f0f2',
          200: '#bcdfe3',
          300: '#93cfd4',
          400: '#4fb7bc',
          500: '#17a398',
          600: '#0e7f73',
          700: '#083042',
          800: '#06262a',
          900: '#031414'
        },
        accent: {
          DEFAULT: '#F6A21A', // bright yellow/orange from the bulb
        },
        accent2: {
          DEFAULT: '#F25C2A' // orange/red accent
        },
        cream: '#FBF6EE'
      }
    },
  },
  plugins: [],
};
