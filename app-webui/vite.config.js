import { defineConfig } from 'vite';
import { svelte } from '@sveltejs/vite-plugin-svelte';
import { resolve } from 'path';

export default defineConfig({
  resolve: {
    alias: {
      '@common': resolve(import.meta.dirname, './src/common'),
    },
  },

  // Watch src files changes
  server: {
    watch: {
      usePolling: true,
      interval: 100 
    }
  },
  
  build: {
    // Prevent asset inlining to ensure all images and fonts are generated as separate files
    assetsInlineLimit: 0,
	
	// Cleanup dist dir
	emptyOutDir: false, 

    rollupOptions: {
      // Array of inputs forces Rollup to treat them as completely separate entry points
      input: {
        'investigation': resolve(import.meta.dirname, 'src/investigation/index.html'),
        'script-manager': resolve(import.meta.dirname, 'src/script-manager/index.html')
      },

      output: {
        assetFileNames: 'assets/[name].[ext]',
        entryFileNames: 'assets/[name].js',
        chunkFileNames: 'assets/[name].js'
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