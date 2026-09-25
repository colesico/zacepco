import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function listScriptEntries(limit, offset){
	return await restletUtils.restletGet("/api/catalog/script-entry",{limit,offset});
}

export async function addScriptEntry(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/api/catalog/script-entry",formData,{});
}