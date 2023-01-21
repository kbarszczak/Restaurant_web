import {Injectable} from '@angular/core';
import {HttpClient, HttpStatusCode} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private http: HttpClient
    private readonly url: string
    error: string

    constructor(http: HttpClient) {
        this.http = http;
        this.url = "http://localhost:9090"
        this.error = ""
    }

    async login(email: string, password: string) {
        await fetch(this.url + "/api/v1/auth/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        }).then(async r => {
            let response = r.json()
            if (r.status != HttpStatusCode.Ok) {
                await response.then(r => this.error = r.message)
                throw Error()
            }
            return response
        }).then(r => {
            let roles: Array<string> = r.body.roles
            localStorage.setItem('token', r.body.token)
            localStorage.setItem('refresh_token', r.body.token)
            localStorage.setItem('name', r.body.name)
            localStorage.setItem('surname', r.body.surname)
            localStorage.setItem('is_admin', roles.indexOf("ROLE_ADMIN") >= 0 ? "true" : "false")
            localStorage.setItem('is_user', roles.indexOf("ROLE_USER") >= 0 ? "true" : "false")
            localStorage.setItem('is_manager', roles.indexOf("ROLE_MANAGER") >= 0 ? "true" : "false")
        }).catch(e => {
            console.log(e)
        })
    }

    logout() {
        localStorage.clear()
    }

    async register(name: string, surname: string, email: string, password: string) {
        await fetch(this.url + "/api/v1/auth/register", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: name,
                surname: surname,
                email: email,
                password: password
            })
        }).then(async r => {
            let response = r.json()
            if (r.status != HttpStatusCode.Ok) {
                await response.then(r => {
                    this.error = r.message
                })
            }
        }).catch(e => {
            console.log(e)
        })
    }

    credentials():string {
        let name = localStorage.getItem("name");
        if(name === null || name === undefined) return ""
        return name.charAt(0).toUpperCase() + name.slice(1).toLowerCase()
    }

    isAdmin() {
        return localStorage.getItem("is_admin") === "true"
    }

    isUser() {
        return localStorage.getItem("is_user") === "true"
    }

    isManager() {
        return localStorage.getItem("is_manager") === "true"
    }

    isLoggedIn() {
        return localStorage.getItem("token") != null
    }
}
