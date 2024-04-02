// audio.service.ts

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AudioService {
  private audio: HTMLAudioElement;

  constructor() {
    this.audio = new Audio();
    this.audio.src = '/assets/pig.soundeffect.mp3'; // ajuste o caminho do arquivo de áudio conforme necessário
  }

  playButtonClickSound() {
    this.audio.currentTime = 0;
    this.audio.play();
  }
}
