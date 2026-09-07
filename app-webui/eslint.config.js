import prettier from 'eslint-config-prettier';
import path from 'node:path';
import js from '@eslint/js';
import svelte from 'eslint-plugin-svelte';
import { defineConfig, includeIgnoreFile } from 'eslint/config';
import globals from 'globals';

const gitignorePath = path.resolve(import.meta.dirname, '.gitignore');

export default defineConfig([
	// Include rules from .gitignore to prevent linting built assets (e.g., dist/)
	includeIgnoreFile(gitignorePath),
	js.configs.recommended,
	svelte.configs.recommended,
	prettier,
	svelte.configs.prettier,
	{
		languageOptions: {
			globals: {
				...globals.browser,
				...globals.node
			}
		}
	},

	{
		files: ['**/*.svelte', '**/*.svelte.js'],
		languageOptions: {
			parserOptions: {
				// Ensures ESLint understands modern JavaScript modules and syntax
				sourceType: 'module',
				ecmaVersion: 2020
			}
		}
	},

	{
		// Global settings to help ESLint resolve file extensions and custom path aliases
		settings: {
			'import/resolver': {
				node: {
					extensions: ['.js', '.json', '.svelte']
				}
			}
		},
		// Override or add specific linting rule settings here
		rules: {}
	}
]);