export interface UserSignUp {
  userName: string;
  userEmail: string;
  userPassword: string;
}

export interface User {
  UserId: number;      // Uppercase - matches your DTO
  UserName: string;    // Uppercase - matches your DTO  
  Email: string;       // Uppercase - matches your DTO (NOT userEmail)
  UserImage?: {
    imageName?: string;
    imageType?: string;
    imageSize?: number;
    imageFile?: string;
  } | null;
}

export interface UserUpdateRequest {
  userName: string;
  userPassword: string;
  userImage?: File | null;
}