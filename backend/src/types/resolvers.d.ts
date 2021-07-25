export type Resolver = (parent: any, args: any, context: any, info: any) => any;

/**
 * d.ts : definition Typescript (형태를 작성하였다는 의미)
 * 이 형태를 준수하는 변수를 사용하겠다.
 */
export interface Resolvers {
  [key: string]: {
    [key: string]: Resolver;
  };
}