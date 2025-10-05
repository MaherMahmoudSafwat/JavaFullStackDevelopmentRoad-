// This file is like the MAIN POWER SWITCH for your entire house

import { bootstrapApplication } from '@angular/platform-browser-dynamic';
// This is like importing the "ELECTRICAL COMPANY'S POWER TOOLS"
// You're calling the power company to connect electricity to your house

import { AppComponent } from './app/app.component';
// This is like saying: "Get the LIVING ROOM CONTROLLER ready"
// You're telling the electrical system which room to power up first

import { config } from './app/app.config';
// This is like importing your "HOUSE ELECTRICAL RULES"
// Things like "use 110V power" and "don't overload circuits"

bootstrapApplication(AppComponent, config)
// This is like FLIPPING THE MAIN BREAKER SWITCH
// You're saying: "Turn on power to the living room using these rules"

  .catch(err => console.error(err));
// This is like having an EMERGENCY BACKUP PLAN
// If the electricity fails, write the error in the "house logbook"