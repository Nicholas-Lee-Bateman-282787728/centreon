module.exports = {
  presets: [
    ['@babel/preset-react'],
    [
      '@babel/preset-env',
      {
        targets: {
          esmodules: false,
        },
      },
    ],
  ],
  plugins: [
    '@babel/plugin-syntax-dynamic-import',
    '@babel/plugin-transform-arrow-functions',
    '@babel/plugin-transform-destructuring',
    '@babel/plugin-transform-function-name',
    '@babel/plugin-transform-parameters',
    '@babel/plugin-proposal-class-properties',
    '@babel/plugin-transform-classes',
    '@babel/plugin-transform-shorthand-properties',
    '@babel/plugin-transform-regenerator',
  ],
  sourceType: 'unambiguous',
};