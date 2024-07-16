import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, NgModule } from '@angular/core';
import { FormBuilder,FormGroup,ReactiveFormsModule,Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,RouterModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  signupForm: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder,private router: Router) {
    this.signupForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  registerUser() {
    if (this.signupForm.valid) {
      this.http
        .post('http://localhost:8080/user/save', this.signupForm.value)
        .subscribe({
          next: (res: any) => {
            console.log(res);
            alert('User Created');
            this.signupForm.reset();
            
          },
          error: (error: any) => {
            console.error('Error:', error);
            alert('Registration failed. Please try again.');
          },
        });
    } else {
      alert('Please fill out the form correctly.');
    }
  }
    
  
  



}
