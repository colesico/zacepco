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
        'script-manager': resolve(import.meta.dirname, 'src/script-manager/index.html'),
      },

      output: {
		assetFileNames: 'assets/[name].[ext]',
        entryFileNames: 'assets/[name].js',
        chunkFileNames: 'assets/[name].js',
		
		manualChunks(id) {
          if (id.includes('node_modules/svelte')) {
            return undefined; 
          }
        }
      }
    }
  },

  plugins: [
    svelte({
      compilerOptions: {
		discloseVersion: false, 
        runes: ({ filename }) =>
          filename.split(/[/\\]/).includes('node_modules') ? undefined : true
      }
    })
  ]
});