import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

export interface Keynote {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  fonction: string;
}

@Component({
  selector: 'app-keynotes',
  imports: [CommonModule],
  templateUrl: './keynotes.component.html',
  standalone: true,
  styleUrl: './keynotes.component.css'
})
export class KeynotesComponent implements OnInit {
  keynotes: Keynote[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Keynote[]>('http://localhost:8080/api/keynotes').subscribe({
      next: (value: Keynote[]) => this.keynotes = value,
      error: (error) => console.error(error)
    });
  }

  openCreateModal(): void {
    // Implement create keynote logic
    console.log('Open create keynote modal');
  }

  editKeynote(keynote: Keynote): void {
    // Implement edit keynote logic
    console.log('Edit keynote', keynote);
  }

  deleteKeynote(id: number): void {
    // Implement delete keynote logic
    this.http.delete(`http://localhost:8080/api/keynotes/${id}`).subscribe({
      next: () => {
        this.keynotes = this.keynotes.filter(keynote => keynote.id !== id);
        console.log('Keynote deleted successfully');
      },
      error: (error) => console.error('Error deleting keynote', error)
    });
  }
}
