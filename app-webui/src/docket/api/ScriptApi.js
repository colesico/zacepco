import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function listScriptEntries(limit, offset){
	return await restletUtils.restletGet("/docket/api/script",{limit,offset});
}

export async function createScriptEntry(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/docket/api/script",formData,{});
}

export async function scriptDocument(scriptEntryId){
	return await restletUtils.restletGet("/docket/api/script/entry/"+scriptEntryId+"/document");
}