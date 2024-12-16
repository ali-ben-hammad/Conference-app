import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClient} from '@angular/common/http';


export enum ConferenceType {

  COMMERCIALE = 'commerciale',
  ACADEMIQUE = 'académique'
}

export interface Conference {
  id: number; // Optional ID, typically used when creating new conferences
  titre: string;
  date: Date;
  duree: number;
  nombreInscrits: number;
  score: number;
  status: ConferenceType;
}

@Component({
  selector: 'app-conferences',
  imports: [CommonModule],
  templateUrl: './conferences.component.html',
  standalone: true,
  styleUrl: './conferences.component.css'

})
export class ConferencesComponent {
 conferences: Conference[] = [];

  constructor(private http : HttpClient) { }

  ngOnInit(): void {

    this.http.get<Conference[]>('http://localhost:8082/api/conferences').subscribe({
      next : (value : Conference[]) =>  this.conferences = value,
      error : (error) => console.error(error)

    });
  }

  openCreateModal(): void {
    // Implement create conference logic
    console.log('Open create conference modal');
  }
  editConference(conference: Conference): void {
    // Implement edit conference logic
    console.log('Edit conference', conference);
  }

  deleteConference(id: number): void {
    // Implement delete conference logic
    this.conferences = this.conferences.filter(conference => conference.id !== id);
  }
}

