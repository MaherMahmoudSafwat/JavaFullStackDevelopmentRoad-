// ========================================
// FILE 1: src/app/app.config.ts
// ========================================
import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()  // This enables HTTP in your entire app
  ]
};
