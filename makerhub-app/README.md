# MakerHub App

A Progressive Web App (PWA) built with React + TypeScript + Vite, ready for deployment to Google Play Store and App Store.

## Features

- ✅ TypeScript for type safety
- ✅ PWA capabilities (offline support, installable)
- ✅ Mobile-ready with Capacitor
- ✅ Bootstrap UI components
- ✅ Ready for app store deployment

## Development

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Type checking
npm run type-check

# Build for production
npm run build
```

## Mobile App Deployment

### Prerequisites
- Android Studio (for Android)
- Xcode (for iOS, macOS only)

### Build and Deploy

```bash
# Build the web app and sync with mobile platforms
npm run build:mobile

# Open Android project
npm run android

# Open iOS project (macOS only)
npm run ios
```

### First-time setup

```bash
# Initialize Capacitor platforms
npx cap add android
npx cap add ios
```

## PWA Features

- Offline functionality
- App-like experience
- Installable on mobile devices
- Push notifications ready
- Background sync capabilities

## App Store Deployment

1. **Android (Google Play Store):**
   - Run `npm run android`
   - Build signed APK/AAB in Android Studio
   - Upload to Google Play Console

2. **iOS (App Store):**
   - Run `npm run ios` (macOS required)
   - Archive and upload via Xcode
   - Submit through App Store Connect

## Configuration

- PWA settings: `vite.config.ts`
- Mobile app settings: `capacitor.config.ts`
- App manifest: `public/manifest.json`