/**
 * Core request handler wrapped around native fetch API.
 * Handles URL parameters serialization, default JSON headers, 401 session expiration, and error parsing.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} [options] - Standard fetch function options extended with custom properties.
 * @param {Object} [queryParams] - Key-value pairs to be appended to the URL as a query string.
 * @returns {Promise<any>} Parsed JSON response data, raw text, or null.
 * @throws {Object} An object containing the response status and data.
 */
export async function restletRequest(url, options, queryParams) {
    
    // Safely fallback to an empty object if options were not passed
    const fetchOptions = options || {};
    
    let finalUrl = url;
 
    // Append query parameters to the URL if provided
    if (queryParams && Object.keys(queryParams).length > 0) {
        const queryString = new URLSearchParams(queryParams).toString();
        finalUrl = `${url}?${queryString}`;
    }

    const currentMethod = (fetchOptions.method || 'GET').toUpperCase();
    const isFormData = fetchOptions.body instanceof FormData;

    // Copy headers and find if there's any user-defined content-type
    const headers = { ...fetchOptions.headers };
    let customContentType = null;

    for (const key in headers) {
        if (key.toLowerCase() === 'content-type') {
            customContentType = headers[key]; // Keep user's custom content-type if it exists
            delete headers[key]; // Remove the old key to prevent casing duplicates
        }
    }

    // Apply final Content-Type in standard casing
    if (!isFormData) {
        headers['Content-Type'] = customContentType || 'application/json';
    }

    // Process body: strict rule - GET methods cannot have a body property in fetch
    let body = undefined;
    if (currentMethod !== 'GET' && fetchOptions.body !== undefined && fetchOptions.body !== null) {
        body = (typeof fetchOptions.body === 'string' || isFormData) ? fetchOptions.body : JSON.stringify(fetchOptions.body);
    }

    try {
        const response = await fetch(finalUrl, { 
            ...fetchOptions,
            method: currentMethod,
            headers, 
            ...(body !== undefined && { body }) // Only inject body if it is allowed and defined
        });

        // Read the response body as plain text first
        const rawData = await response.text();

        let data = null;
        if (rawData) {
            try {
                // Try to parse as JSON first
                data = JSON.parse(rawData);
            } catch (e) {
                // Fallback to raw text if it's not a valid JSON (e.g., HTML, XML, plain text)
                data = rawData;
            }
        }

        // Handle 401 Unauthorized globally (session expiration)
        if (response.status === 401) {
            console.warn(`Response status 401. Redirecting to login.`);
            window.location.href = '/login'; 
            return null; 
        }

        // Handle bad HTTP status codes (400, 500, etc.)
        if (!response.ok) {
            // Create a specific application error object containing status and response data
            throw {
                status: response.status,
                data: data
            };
        }

        return data;
    } catch (error) {
        // If it's our custom thrown error object, log it nicely
        if (error && typeof error === 'object' && 'status' in error) {
            console.error(`[API Error ${error.status}] Request to ${finalUrl} failed:`, error.data);
            const msg = error.data?.message || "Server error";
            alert(`Failed to load data: ${msg}`);
        } else {
            // Handle native fetch/network errors (e.g., CORS, Internet disconnected)
            console.error(`[System Error] Request to ${finalUrl} failed:`, error);
            alert(`Failed to load data: ${error?.message || "Unknown error"}`);
        }
        
        throw error;
    }
}


/**
 * Sends a GET request to retrieve data from the server.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} [queryParams] - Query parameters to append to the URL (?key=value).
 * @returns {Promise<any>} The server response data.
 */
export function restletGet(url, queryParams) {
  return restletRequest(url, { method: 'GET' }, queryParams);
}

/**
 * Sends a POST request to create a new resource on the server.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} body - The data payload to be created (will be JSON-serialized).
 * @param {Object} [queryParams] - Optional query parameters to append to the URL.
 * @returns {Promise<any>} The server response data.
 */
export function restletPost(url, body, queryParams) {
  return restletRequest(url, { method: 'POST', body }, queryParams);
}

/**
 * Sends a PUT request to update an existing resource on the server.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} body - The data payload to be updated (will be JSON-serialized).
 * @param {Object} [queryParams] - Optional query parameters to append to the URL.
 * @returns {Promise<any>} The server response data.
 */
export function restletPut(url, body, queryParams) {
  return restletRequest(url, { method: 'PUT', body }, queryParams);
}

/**
 * Sends a PATCH request to partially update an existing resource on the server.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} body - The data payload to be patched.
 * @param {Object} [queryParams] - Optional query parameters to append to the URL.
 * @returns {Promise<any>} The server response data.
 */
export function restletPatch(url, body, queryParams) {
  return restletRequest(url, { method: 'PATCH', body }, queryParams);
}

/**
 * Sends a DELETE request to remove a specific resource from the server.
 * 
 * @param {string} url - The target endpoint URL.
 * @param {Object} [queryParams] - Optional query parameters to identify the resource.
 * @returns {Promise<any>} The server response data.
 */
export function restletDelete(url, queryParams) {
  return restletRequest(url, { method: 'DELETE' }, queryParams);
}
