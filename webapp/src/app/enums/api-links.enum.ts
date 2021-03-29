export enum ApiLinksEnum {
  LIVE_DATA = 'http://localhost:8080/api/getLiveData',
  NEWS = 'http://localhost:8080/api/news/general',
  NEWS_VACC = 'http://localhost:8080/api/news/vaccins',
  VAC_CENTRES = 'http://localhost:8080/api/vaccins/centres',
  SUBSCRIBE = 'http://localhost:8080/api/subscribe',
  METRICS = 'http://localhost:8080/api/metrics',
  METRICS_RANGE = 'http://localhost:8080/api/metrics/range'
}

// tslint:disable-next-line:no-namespace
export namespace ApiLinksEnum {
  export function insertParam(path: ApiLinksEnum, param: string, index: number): string {
    return(path.replace('$' + index, param));
  }
}
