import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  // properties for the component
  sections ={
    hero: {
      title: 'Optimisez votre planning et le temps passé à le réaliser',
      description: 'Ne perdez plus de temps à jongler entre différents fichiers Excel ou différents logiciels, centralisezz et automatisez l\'organisation de votre exploitation déchets avec Neurowaste !',
      buttonText: 'Mon Portail',
      buttonLink: '/portal',
      imageUrl: './assets/img/planning.jpg.avif'
    },

    analysis:{
      title: 'Analyse vos résultats de collecte facilement',
      description: 'Les tonnages et opérations réalisés...',
      buttonText: 'Analyses et données',
      buttonLink: '/analysis',
      imageUrl: './assets/img/analysis.jpeg'
    },

    service:{
      title: 'Simplifier la gestion de votre service déchet',
      description: 'Ne perdez plus le temps à jongler entre différents fichiers Excel ou defférents logiciels, centralisez et automatisez l\'organisation votre exploitation déchets avec Neurowaste !',
      buttonText: 'Contactez Nous',
      buttonLink: '/contact',
      imageUrl: './assets/img/truck.png'
    }

  };
service: any;

}
