FROM node:22-alpine
ARG SERVICIO
WORKDIR /app
COPY package.json package-lock.json ./
COPY comun ./comun
COPY auth-service/package.json ./auth-service/
COPY inventario-service/package.json ./inventario-service/
COPY pedidos-service/package.json ./pedidos-service/
COPY api-gateway/package.json ./api-gateway/
RUN npm ci --omit=dev
COPY ${SERVICIO} ./${SERVICIO}
WORKDIR /app/${SERVICIO}
ENV NODE_ENV=production
CMD ["node", "index.js"]
