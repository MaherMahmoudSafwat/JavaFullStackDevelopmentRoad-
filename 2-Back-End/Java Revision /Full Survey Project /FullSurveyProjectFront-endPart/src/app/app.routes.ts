import { Routes } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome';
import { SignUpComponent } from './components/sign-up/sign-up';
import { SignInComponent } from './components/sign-in/sign-in';
import { DashboardComponent } from './components/dashboard/dashboard';
import { UserProfileComponent } from './components/user-profile/user-profile';
import { EditProfileComponent } from './components/edit-profile/edit-profile';
import { SurveyComponent } from './components/survey/survey';
import { MySurveysComponent } from './components/my-surveys/my-surveys'; // Correct path

export const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'welcome', component: WelcomeComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'edit-profile', component: EditProfileComponent },
  { path: 'add-survey', component: SurveyComponent },
  { path: 'my-surveys', component: MySurveysComponent },
  { path: 'all-surveys', redirectTo: '/dashboard' },
  { path: 'edit-survey', redirectTo: '/dashboard' },
  { path: 'delete-survey', redirectTo: '/dashboard' }
];