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
    // Disable asset inlining so small icons are forced to build as separate physical files
    assetsInlineLimit: 0,

    rollupOptions: {
      input: {
        investigation: resolve(import.meta.dirname, 'src/investigation/index.html'),
        scriptEditor: resolve(import.meta.dirname, 'src/script-editor/index.html'),
      },

      output: {
        // Output JS files named
        entryFileNames: 'assets/[name]/[name].js',
        chunkFileNames: 'assets/[name]/chunks/[name].js',


        // Fixed asset separation logic by checking the original file path
        assetFileNames: (assetInfo) => {
          const fullPath = assetInfo.originalFileName
            ? assetInfo.originalFileName.replace(/\\/g, '/')
            : (assetInfo.name || '');

          let folder = 'common';
          if (fullPath.includes('investigation')) {
            folder = 'investigation';
          } else if (fullPath.includes('script-editor') || fullPath.includes('scriptEditor')) {
            folder = 'scriptEditor';
          }

          // FIX: Check if the asset is a CSS file, including Svelte style queries
          if (fullPath.includes('.css') || assetInfo.name?.endsWith('.css')) {
            return `assets/${folder}/${folder}.css`;
          }

          // Keep hashes for images and fonts to prevent aggressive browser caching
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