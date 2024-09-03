export const categoryMapping: { [key: string]: string } = {
  career: '진로',
  health: '건강',
  relationships: '인간 관계',
  money: '돈',
  goals: '목표',
  studies: '학업/성적',
  other: '기타',
};

export function mapCategoryToKorean(category: string): string {
  return categoryMapping[category] || '기타'; // 기본적으로 '기타'로 매핑
}
