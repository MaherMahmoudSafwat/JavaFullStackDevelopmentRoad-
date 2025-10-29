import { Routes } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome';
import { SignUpComponent } from './components/sign-up/sign-up';
import { SignInComponent } from './components/sign-in/sign-in';
import { DashboardComponent } from './components/dashboard/dashboard';
import { UserProfileComponent } from './components/user-profile/user-profile';
import { EditProfileComponent } from './components/edit-profile/edit-profile';
import { SurveyComponent } from './components/survey/survey';
import { MySurveysComponent } from './components/my-surveys/my-surveys';
import { EditSurveyComponent } from './components/edit-survey/edit-survey';
import { EditQuestionComponent } from './components/edit-question/edit-question';
import { DeleteSurveyComponent } from './components/delete-survey/delete-survey';
import { ShowAllSurveysComponent } from './components/show-all-surveys/show-all-surveys';

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
  { path: 'edit-survey', component: EditSurveyComponent },
  { path: 'edit-question', component: EditQuestionComponent },
  { path: 'delete-survey', component: DeleteSurveyComponent },
  { path: 'show-all-surveys', component: ShowAllSurveysComponent }
];