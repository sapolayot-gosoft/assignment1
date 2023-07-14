export interface CreateUser {
    data: CreateOrUpdateUserInput;
}
export interface CreateOrUpdateUserInput {
    username: string;
    fullName: string | null;
    phone: string;
    oldPassword: string | null;
    password: string;
    rePassword: string | null;
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

// export interface ProductCreateInput {
//     SKU: string;
//     price: number;
//     name: string;
//     detail: string;
// }
export interface ProductCreateOrUpdateInput {
    id: number | null;
    SKU: string;
    price: number;
    name: string;
    detail: string;
}

export interface ProductDeleteInput {
    id: number;
}