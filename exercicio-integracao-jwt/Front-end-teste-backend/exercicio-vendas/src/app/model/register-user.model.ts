import { UserRole } from "./user-role.enum";

export interface RegisterUser {
    login: string,
    password: string,
    role: UserRole
}