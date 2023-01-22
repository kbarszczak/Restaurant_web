import {Component} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {

    auth: AuthService
    router: Router

    name: string
    nameError: string
    surname: string
    surnameError: string
    email: string
    emailError: string
    password: string
    passwordError: string

    constructor(auth: AuthService, router: Router) {
        this.auth = auth
        this.router = router
        this.name = ""
        this.nameError = ""
        this.surname = ""
        this.surnameError = ""
        this.email = ""
        this.emailError = ""
        this.password = ""
        this.passwordError = ""
    }

    updateName(event: any) {
        this.name = event.target.value
    }

    updateSurname(event: any) {
        this.surname = event.target.value
    }

    updateEmail(event: any) {
        this.email = event.target.value
    }

    updatePassword(event: any) {
        this.password = event.target.value
    }

    cancel() {
        this.router.navigate(['/home']).catch(e => console.log(e))
    }

    async submit() {
        this.nameError = ""
        this.surnameError = ""
        this.emailError = ""
        this.passwordError = ""

        if (this.name === "") this.nameError = "Name cannot be empty"
        if (this.surname === "") this.surnameError = "Surname cannot be empty"
        if (this.email === "") this.emailError = "Email cannot be empty"
        if (this.password === "") this.passwordError = "Password cannot be empty"
        if (this.emailError !== "" || this.passwordError !== "" || this.nameError || this.surnameError) return

        await this.auth.register(this.name, this.surname, this.email, this.password)
        if (this.auth.error !== ""){
            this.passwordError = this.auth.error
            this.auth.error = ""
            return
        }
        this.router.navigate(["/home"]).catch(e => console.log(e))
    }

}
