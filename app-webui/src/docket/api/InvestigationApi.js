import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function createInvestigation(request){
	return await restletUtils.restletPost("/docket/api/investigation/new",request,{});
}

export async function findInvestigationContext(id){
	return await restletUtils.restletGet(`/docket/api/investigation/${id}/context`);
}

export async function search(investigationId, entityId, detectiveFocus, d12) {
    const request = { focus: detectiveFocus, d12 };
    const url = `/docket/api/investigation/${investigationId}/search/${entityId}`;
    
    return restletUtils.restletPut(url, request);
}

export async function interrogate(investigationId, personageId, detectiveFocus, d12) {
    const request = { focus: detectiveFocus, d12 };
    const url = `/docket/api/investigation/${investigationId}/interrogation/${personageId}`;
    
    return restletUtils.restletPut(url, request);
}

export async function findPersonalInvestigation(scriptId) {
	const params = { script : scriptId }
    const url = `/docket/api/investigation/personal`;
    return restletUtils.restletGet(url, params);
}

