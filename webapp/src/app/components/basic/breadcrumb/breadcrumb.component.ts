import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

export interface Folder {
  id: string;
  name: string;
}

// @ts-ignore
@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss']
})
export class BreadcrumbComponent implements OnInit {
  @Input() folders: Folder[];
  @Output() selected = new EventEmitter<string>();
  prepend = '';

  constructor() { }

  ngOnInit(): void {
  }

  addFolder(id: string, name: string): void {
    this.folders.push({
      id,
      name
    });
  }

  popFolder(): void {
    if (this.folders.length > 1) { this.folders.pop(); }
    console.log(this.folders);
  }

  onClick(folder: Folder): void {
    const i = this.getIndexOf(folder);
    if (i === -1) { return; }
    if (i !== this.folders.length - 1) {
      this.folders.splice(i - this.folders.length + 1);
    }
    this.selected.emit(this.folders[i].id);
  }

  // UTIL
  getIndexOf(folder: Folder): number {
    let i;
    for (i = 0; i < this.folders.length; i++) {
      if (this.folders[i] === folder) {
        return i;
      }
    }
    return -1;
  }

}
