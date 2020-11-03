
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book.model';

type EntityResponseType = HttpResponse<Book>;
type EntityArrayResponseType = HttpResponse<Book[]>;

@Injectable({
    providedIn: 'root'
})
export class BookService {

  private baseUrl = 'http://localhost:8080/api/books';
  private resourceSearchUrl = 'http://localhost:8080/api/_search/books';

  constructor(private http: HttpClient) {
  }

  find(id: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/${id}`);
  }

  create(book: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, book);
  }

  update(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  findAll(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<Book[]>(this.baseUrl, { params: options, observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<Book[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }

  private createRequestOption(req?: any): HttpParams {
    let options: HttpParams = new HttpParams();
    if (req) {
        options = options.set('page', req.page);
        options = options.set('size', req.size);
        if (req.sort) {
            options.set('sort', req.sort);
        }
        if (req.query) {
            options = options.set('query', req.query);
        }
    }
    return options;
  }
}





