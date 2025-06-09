module.exports = {
  type: 'postgres',
  host: process.env.DB_HOST || 'localhost',
  port: parseInt(process.env.DB_PORT, 10) || 5432,
  username: process.env.DB_USER || '',
  password: process.env.DB_PASS || '',
  database: process.env.DB_NAME || '',
  synchronize: true,
  logging: false,
  entities: [
    'dist/**/*.entity{.ts,.js}',
  ],
  migrations: [
    'dist/migration/**/*.js',
  ],
  subscribers: [
    'dist/subscriber/**/*.js',
  ],
};