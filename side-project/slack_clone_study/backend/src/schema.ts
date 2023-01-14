/**
 * 
 */
import { fileLoader, mergeResolvers, mergeTypes } from "merge-graphql-schemas";
import { makeExecutableSchema } from "graphql-tools";
import path from "path";

/**
 * graphql 확장자인 모든 파일을 가져옴
 */
const allTypes: any = fileLoader(path.join(__dirname, "./api/**/*.graphql")); // graphql을 확장자로 파일들을 만들 것이고,

/**
 * resolvers 확장자인 모든 파일을 가져옴
 */
const allResolvers: any = fileLoader(
  path.join(__dirname, "./api/**/*.resolvers.*") // resolvers을 확장자로 파일들을 만들것이다.
);

const mergedTypes = mergeTypes(allTypes);
const mergedResolvers: any = mergeResolvers(allResolvers);

const schema = makeExecutableSchema({
  typeDefs: mergedTypes,
  resolvers: mergedResolvers
});

export default schema;