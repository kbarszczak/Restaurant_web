export class User{

    id: string
    name: string
    surname: string
    email: string
    password: string
    roles: Array<string>

    constructor(id: string, name: string, surname: string, email: string, password: string, roles: Array<string>) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}