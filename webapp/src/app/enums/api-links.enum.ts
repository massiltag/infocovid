export enum ApiLinksEnum {
  LIVE_DATA = 'http://localhost:8080/api/getLiveData',
  NEWS = 'http://localhost:8080/api/getNews'
}

// tslint:disable-next-line:no-namespace
export namespace ApiLinksEnum {
  export function insertParam(path: ApiLinksEnum, param: string, index: number): string {
    return(path.replace('$' + index, param));
  }
}
