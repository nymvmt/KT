export const profileData = {
  name: '김나영',
  role: 'IT Consultant',
  experience: '신입사원 (열심히 배우는 중!)',
  location: 'Seoul, South Korea',
  education: '고려대학교 미디어학부/컴퓨터학과',
  major: '미디어 플랫폼 비즈니스 / 컴퓨터 엔지니어링',
  email: 'nayoungkim.work@gmail.com',
  phone: '010-7300-9007',
  workHours: '월-금 9:00 - 18:00',
  status: '온라인',
  country: '대한민국',
  description: '사용자 중심의 혁신적인 IT 솔루션을 제공하여 더 나은 디지털 경험을 만들어가는 것이 저의 목표입니다.'
};

export const socialLinks = [
  {
    name: 'Facebook',
    icon: '📘',
    url: '#',
    color: 'from-blue-500 to-blue-600',
    username: '@nayoung.kim'
  },
  {
    name: 'Twitter',
    icon: '🐦',
    url: '#',
    color: 'from-cyan-500 to-cyan-600',
    username: '@nayoung_kim'
  },
  {
    name: 'Instagram',
    icon: '📷',
    url: '#',
    color: 'from-emerald-500 to-emerald-600',
    username: '@nayoung.kim'
  },
  {
    name: 'LinkedIn',
    icon: '💼',
    url: '#',
    color: 'from-slate-500 to-slate-600',
    username: '김나영'
  }
];

export const navigationLinks = [
  { name: '소개', href: '/about', icon: '👋' },
  { name: '좋아하는 것', href: '/favorites', icon: '💖' },
  { name: '연락처', href: '/contact', icon: '📞' }
];

export const favoritesData = {
  foods: [
    { name: '피자', description: '치즈가 듬뿍!', icon: '🍕', color: 'from-blue-500 to-cyan-500' },
    { name: '라멘', description: '진한 육수', icon: '🍜', color: 'from-emerald-500 to-teal-500' },
    { name: '케이크', description: '달콤한 디저트', icon: '🍰', color: 'from-cyan-500 to-blue-500' },
    { name: '커피', description: '아메리카노', icon: '☕', color: 'from-slate-500 to-gray-600' }
  ],
  hobbies: [
    { name: '독서', description: '소설, 자기계발서', icon: '📚', color: 'from-blue-500 to-cyan-500' },
    { name: '음악 감상', description: '팝, 인디', icon: '🎵', color: 'from-emerald-500 to-teal-500' },
    { name: '게임', description: '모바일 게임', icon: '🎮', color: 'from-cyan-500 to-blue-500' },
    { name: '운동', description: '요가, 걷기', icon: '🏃‍♀️', color: 'from-slate-500 to-gray-600' }
  ],
  colors: [
    { name: '파랑', description: '차분한', icon: '💙', color: 'from-blue-400 to-cyan-600' },
    { name: '초록', description: '자연스러운', icon: '💚', color: 'from-emerald-400 to-teal-600' },
    { name: '청록', description: '신선한', icon: '💎', color: 'from-cyan-400 to-blue-600' },
    { name: '검정', description: '깔끔한', icon: '⚫', color: 'from-slate-400 to-gray-600' }
  ],
  places: [
    { name: '집', description: '편안한 공간', icon: '🏠', color: 'from-blue-500 to-cyan-500' },
    { name: '카페', description: '분위기 좋은', icon: '☕', color: 'from-emerald-500 to-teal-500' },
    { name: '도서관', description: '조용한 공간', icon: '📚', color: 'from-cyan-500 to-blue-500' },
    { name: '공원', description: '자연 속에서', icon: '🌳', color: 'from-slate-500 to-gray-600' }
  ]
};

export const skillsData = [
  {
    name: '시스템 분석',
    description: '비즈니스 요구사항 분석',
    icon: '🔧',
    color: 'from-blue-500 to-cyan-500'
  },
  {
    name: '데이터 분석',
    description: '인사이트 도출',
    icon: '📊',
    color: 'from-emerald-500 to-teal-500'
  },
  {
    name: 'UX/UI 설계',
    description: '사용자 경험 설계',
    icon: '🎨',
    color: 'from-cyan-500 to-blue-500'
  },
  {
    name: '프로젝트 관리',
    description: '효율적인 프로젝트 진행',
    icon: '🚀',
    color: 'from-slate-500 to-gray-600'
  }
];

export const contactInfo = [
  {
    type: '이메일',
    value: profileData.email,
    icon: '📧',
    color: 'from-blue-500 to-cyan-500'
  },
  {
    type: '전화번호',
    value: profileData.phone,
    icon: '📱',
    color: 'from-emerald-500 to-teal-500'
  },
  {
    type: '위치',
    value: profileData.location,
    icon: '📍',
    color: 'from-cyan-500 to-blue-500'
  },
  {
    type: '업무 시간',
    value: profileData.workHours,
    icon: '💼',
    color: 'from-slate-500 to-gray-600'
  }
]; 