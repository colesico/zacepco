import { defineConfig } from 'vite';
import { svelte } from '@sveltejs/vite-plugin-svelte';
import { resolve } from 'path';

export default defineConfig({
  resolve: {
    alias: {
      '@common': resolve(import.meta.dirname, './src/common'),
    },
  },

  build: {
    // Prevent asset inlining to ensure all images and fonts are generated as separate files
    assetsInlineLimit: 0,

    rollupOptions: {
      input: {
        investigation: resolve(import.meta.dirname, 'src/investigation/index.html'),
        'script-editor': resolve(import.meta.dirname, 'src/script-editor/index.html'),
      },

      output: {
        // Output structure for JS entry files and chunks
        entryFileNames: 'assets/[name]/[name].js',
        chunkFileNames: 'assets/[name]/chunks/[name].js',

        // Distribute assets into separate folders based on their source directory
        assetFileNames: (assetInfo) => {
          const fullPath = assetInfo.originalFileName
            ? assetInfo.originalFileName.replace(/\\/g, '/')
            : (assetInfo.name || '');

          let folder = 'common';
          if (fullPath.includes('investigation')) {
            folder = 'investigation';
          } else if (fullPath.includes('script-editor') || fullPath.includes('scriptEditor')) {
            folder = 'script-editor';
          }

          // Output CSS files with a fixed name per module
          if (fullPath.includes('.css') || assetInfo.name?.endsWith('.css')) {
            return `assets/${folder}/${folder}.css`;
          }

          // Output static assets with hashes to prevent aggressive browser caching
          return `assets/${folder}/[name]-[hash].[ext]`;
        }
      }
    }
  },

  plugins: [
    svelte({
      compilerOptions: {
        runes: ({ filename }) =>
          filename.split(/[/\\]/).includes('node_modules') ? undefined : true
      }
    })
  ]
});