import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function listScripts(limit, offset){
	return await restletUtils.restletGet("/docket/api/script",{limit,offset});
}

export async function createScript(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/docket/api/script",formData,{});
}

export async function scriptDocument(scriptId){
	return await restletUtils.restletGet("/docket/api/script/"+scriptId+"/document");
}