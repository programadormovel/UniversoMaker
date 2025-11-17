# Mobile App Setup Guide

## Quick Setup for App Store Deployment

### 1. Initialize Mobile Platforms

```bash
# Add Android platform
npx cap add android

# Add iOS platform (macOS only)
npx cap add ios
```

### 2. Build and Sync

```bash
# Build web app and sync to mobile platforms
npm run build:mobile
```

### 3. Open in Native IDEs

```bash
# Open Android Studio
npm run android

# Open Xcode (macOS only)
npm run ios
```

### 4. Configure App Details

**Android (android/app/src/main/AndroidManifest.xml):**
- Update app name, permissions
- Configure app icons in `android/app/src/main/res/`

**iOS (ios/App/App/Info.plist):**
- Update app name, bundle ID
- Configure app icons in `ios/App/App/Assets.xcassets/`

### 5. Generate App Icons

Use a tool like [PWA Asset Generator](https://github.com/elegantapp/pwa-asset-generator) or create icons manually:

- Android: 48x48, 72x72, 96x96, 144x144, 192x192, 512x512
- iOS: 20x20, 29x29, 40x40, 58x58, 60x60, 76x76, 80x80, 87x87, 120x120, 152x152, 167x167, 180x180, 1024x1024

### 6. Build for Release

**Android:**
1. Generate signed APK/AAB in Android Studio
2. Upload to Google Play Console

**iOS:**
1. Archive in Xcode
2. Upload to App Store Connect

## Important Notes

- Update `capacitor.config.ts` with your app ID
- Replace placeholder icons in `/public/` folder
- Test on real devices before publishing
- Follow platform-specific guidelines for app store approval