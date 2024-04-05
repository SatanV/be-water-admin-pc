import service from '@/utils/request'

export const useM_short_linksApi = (id: number) => {
	return service.get('/shortchain/m_short_links/' + id)
}

export const useM_short_linksSubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('/shortchain/m_short_links', dataForm)
	} else {
		return service.post('/shortchain/m_short_links', dataForm)
	}
}