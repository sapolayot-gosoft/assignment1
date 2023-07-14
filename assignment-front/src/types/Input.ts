export interface CreateUser {
    data: CreateOrUpdateUserInput;
}
export interface CreateOrUpdateUserInput {
    username: string;
    // fullName: string;
    phone: string;
    password: string;
    rePassword: string;
}
export interface LoginUserInput {
    username: string;
    password: string;
}
export interface FileInput {
    name: string;
}

export interface SearchInput {
    search: string
}

export interface ProductUpdateInput {
    id: number | null;
    SKU: string;
    price: number;
    name: string;
    detail: string;
}

export interface ProductDeleteInput {
    id: number;
}