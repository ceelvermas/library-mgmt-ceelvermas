import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BookLadger } from './bookLadger.model';

type EntityResponseType = HttpResponse<BookLadger>;
type EntityArrayResponseType = HttpResponse<BookLadger[]>;

@Injectable({
    providedIn: 'root'
})
export class BookLadgerService {

  private baseUrl = 'http://localhost:8080/api/book-ledgers';
  private resourceSearchUrl = 'http://localhost:8080/api/_search/book-ladgers';

  constructor(private http: HttpClient) {
  }

  find(id: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/${id}`);
  }

  create(bookLadger: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, bookLadger);
  }

  update(bookLadger: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}`, bookLadger);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  findAll(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<BookLadger[]>(this.baseUrl, { params: options, observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<BookLadger[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
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
