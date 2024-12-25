export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse{
    token: string;
    user: User;
}

export interface User{
    id: number;
    username: string;
    email: string;
    role: UserRole;
    firstName?: string;
    lastName?: string;
    isActive: boolean;
    lastLogin?: Date;
}

export enum UserRole {
    ADMIN = 'ADMIN',
    DRIVER = 'DEIVER',
    OPERATOR = 'OPERATOR'
}

export interface AuthState {
    isAuthenticated: boolean;
    user: User | null;
    token: string | null;
}
