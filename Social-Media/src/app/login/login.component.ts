import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string |null=null;
  

  constructor(private fb: FormBuilder, private http: HttpClient,private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,Validators.minLength(6)]]
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      this.http.post('http://localhost:8080/user/login', formData, { responseType: 'text' })
        .subscribe({
          next: (response: any) => {
            console.log(response);
            this.router.navigate(['/home']);
          },
          error: (error) => {
            console.error('Login error:', error);
            this.loginError = 'Failed to login. Please try again.';
          }
        });
    }
  }

  

}
