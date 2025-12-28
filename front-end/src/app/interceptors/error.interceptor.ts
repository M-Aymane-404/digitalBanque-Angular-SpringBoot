import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export type BackendApiError = {
  code?: string;
  message?: string;
};

export type NormalizedHttpError = {
  httpStatus: number;
  code?: string;
  message: string;
  raw?: unknown;
};

function normalizeHttpError(err: unknown): NormalizedHttpError {
  if (!(err instanceof HttpErrorResponse)) {
    return {
      httpStatus: -1,
      message: 'Unexpected error occurred.',
      raw: err,
    };
  }

  const status = err.status;

  if (status === 0) {
    return {
      httpStatus: 0,
      message: 'Unable to reach the server. Please check your connection and try again.',
      raw: err,
    };
  }

  const backendPayload = err.error as BackendApiError | null | undefined;
  const backendCode = backendPayload?.code;
  const backendMessage = backendPayload?.message;

  if (backendCode || backendMessage) {
    return {
      httpStatus: status,
      code: backendCode,
      message: backendMessage ?? 'Request failed.',
      raw: err,
    };
  }

  if (typeof err.message === 'string' && err.message.trim().length > 0) {
    return {
      httpStatus: status,
      message: err.message,
      raw: err,
    };
  }

  if (status === 401) {
    return {
      httpStatus: 401,
      message: 'You are not authenticated. Please login again.',
      raw: err,
    };
  }

  if (status === 403) {
    return {
      httpStatus: 403,
      message: 'You are not authorized to perform this action.',
      raw: err,
    };
  }

  if (status >= 500) {
    return {
      httpStatus: status,
      message: 'An unexpected server error occurred. Please try again later.',
      raw: err,
    };
  }

  return {
    httpStatus: status,
    message: 'Request failed.',
    raw: err,
  };
}

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((err) => {
      const normalized = normalizeHttpError(err);
      return throwError(() => normalized);
    })
  );
};
