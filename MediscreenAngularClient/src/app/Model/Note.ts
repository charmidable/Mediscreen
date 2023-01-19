export class Note
{
  id        !: string;
  note      !: string;
  patientId !: number;
  date      !: string;

  getDateAsDate()
  {
    return new Date(this.date);
  }

  static fromHttp(httpNote: Note) : Note
  {
    const note = new Note();
    note.id = httpNote.id;
    note.note = httpNote.note;
    note.date = httpNote.date;
    note.patientId = httpNote.patientId;
    return note;
  }
}
