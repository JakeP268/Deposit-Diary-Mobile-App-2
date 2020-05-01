package ie.wit.models;

interface DepositDiaryStore {
    fun findAll() : List<DepositDiaryModel>
    fun findById(id: Long) : DepositDiaryModel?
    fun create(depositDiary: DepositDiaryModel)
    //fun update(donation: DonationModel)
    //fun delete(donation: DonationModel)
}