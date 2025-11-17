# Configuração Android - Universo Maker

## Permissões Configuradas

### ✅ Internet
- `INTERNET` - Acesso à internet
- `ACCESS_NETWORK_STATE` - Estado da rede

### ✅ Câmera
- `CAMERA` - Uso da câmera
- Hardware: `android.hardware.camera` (opcional)

### ✅ Armazenamento
- `READ_EXTERNAL_STORAGE` - Leitura de arquivos
- `WRITE_EXTERNAL_STORAGE` - Escrita de arquivos
- `READ_MEDIA_IMAGES` - Leitura de imagens (Android 13+)
- `READ_MEDIA_VIDEO` - Leitura de vídeos (Android 13+)

### ✅ Localização
- `ACCESS_COARSE_LOCATION` - Localização aproximada
- `ACCESS_FINE_LOCATION` - Localização precisa
- Hardware: `android.hardware.location` (opcional)
- Hardware: `android.hardware.location.gps` (opcional)

### ✅ Notificações
- `POST_NOTIFICATIONS` - Envio de notificações (Android 13+)
- `WAKE_LOCK` - Manter dispositivo ativo
- `VIBRATE` - Vibração

## Plugins Capacitor Instalados

- `@capacitor/camera` - Câmera e galeria
- `@capacitor/geolocation` - GPS e localização
- `@capacitor/local-notifications` - Notificações locais
- `@capacitor/push-notifications` - Notificações push
- `@capacitor/filesystem` - Sistema de arquivos

## Como Compilar

```bash
# Compilar e abrir no Android Studio
npm run build:android

# Ou manualmente
npm run build
npx cap sync android
npx cap open android
```

## Configurações Adicionais

- **App ID**: `com.universomaker.app`
- **Scheme**: HTTPS
- **File Provider**: Configurado para compartilhamento de arquivos
- **Clear Text Traffic**: Habilitado para desenvolvimento

## Próximos Passos

1. Abrir projeto no Android Studio
2. Configurar chaves de assinatura
3. Testar em dispositivo real
4. Gerar APK/AAB para publicação