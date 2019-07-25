import Swagger from 'swagger-client'

export const HOST = window.location.host.split(':')[0]

export const publicClient = async function () {
    const client = await Swagger('http://'+HOST+':8080/v2/api-docs')
    return client
}

export const basicAuthClient = async function (username, password) {
    const client = await Swagger('http://'+HOST+':8080/v2/api-docs', {
        authorizations: {
            basicAuth: {username: username, password: password}
        }
    })
    return client
}