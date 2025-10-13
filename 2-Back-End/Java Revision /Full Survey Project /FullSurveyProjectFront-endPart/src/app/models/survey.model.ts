export interface SurveyQuestion {
  question: string;
  answers: string[];
}

export interface Survey {
  id?: number;
  questions: SurveyQuestion[];
}
