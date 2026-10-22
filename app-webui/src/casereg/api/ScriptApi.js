import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function listScriptEntries(limit, offset){
	return await restletUtils.restletGet("/casereg/api/script",{limit,offset});
}

export async function createScriptEntry(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/casereg/api/script",formData,{});
}

export async function scriptOverview(scriptEntryId){
	return await restletUtils.restletGet("/casereg/api/script/overview/"+scriptEntryId);
}