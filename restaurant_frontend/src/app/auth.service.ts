import {Injectable} from '@angular/core';
import {HttpClient, HttpStatusCode} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private http: HttpClient
    private readonly url: string
    jwt: string;
    refreshJwt: string;
    userName: string
    roles: Array<string>
    error: string

    constructor(http: HttpClient) {
        this.http = http;
        this.url = "http://localhost:9090"
        this.jwt = ""
        this.refreshJwt = ""
        this.userName = ""
        this.roles = []
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
        }).then(r => {
            if (r.status != HttpStatusCode.Ok) throw Error()
            return r.json()
        }).then(r => {
            this.userName = r.body.name
            this.jwt = r.body.token
            this.refreshJwt = r.body.refreshToken
            this.roles = r.body.roles
        }).catch(e => {
            this.error = "Bad credentials"
        })
    }

    logout() {
        this.jwt = ""
        this.refreshJwt = ""
        this.roles = []
    }

    register(name: string, surname: string, email: string, password: string): string {
        // todo: register
        return ""
    }

    hasRole(role: string) {
        return role.indexOf(role) >= 0
    }

    isAdmin() {
        return this.hasRole("ROLE_ADMIN")
    }

    isUser() {
        return this.hasRole("ROLE_USER")
    }

    isManager() {
        return this.hasRole("ROLE_MANAGER")
    }

    isLoggedIn() {
        return this.jwt !== ""
    }

}
