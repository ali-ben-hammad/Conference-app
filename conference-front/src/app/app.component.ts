import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, CommonModule],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'wallet-front';
  public profile: KeycloakProfile | null = null;
  public isLoading = true;

  constructor(private keycloakService: KeycloakService) {}

  async ngOnInit(): Promise<void> {
    try {
      this.isLoading = true;
      const isLoggedIn = await this.keycloakService.isLoggedIn();

      if (isLoggedIn) {
        try {
          this.profile = await this.keycloakService.loadUserProfile();
        } catch (error) {
          console.error('Error loading user profile', error);
          // Optionally handle profile loading error
        }
      }
    } catch (error) {
      console.error('Authentication check failed', error);
    } finally {
      this.isLoading = false;
    }
  }

  async handleLogin(): Promise<void> {
    try {
      await this.keycloakService.login({
        redirectUri: window.location.origin
      });
    } catch (error) {
      console.error('Login failed', error);
      // Optionally show user-friendly error message
    }
  }

  async handleLogout(): Promise<void> {
    try {
      await this.keycloakService.logout(window.location.origin);
    } catch (error) {
      console.error('Logout failed', error);
      // Optionally show user-friendly error message
    }
  }

  get isAuthenticated(): boolean {
    return this.profile !== null;
  }
}
