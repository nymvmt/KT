'use client';

import ProfileCard from '../../components/ProfileCard';
import ContactForm from '../../components/ContactForm';
import SocialMedia from '../../components/SocialMedia';

export default function Contact() {
  return (
    <div className="max-w-6xl mx-auto p-8">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold mb-6 bg-gradient-to-r from-white via-blue-200 to-cyan-200 bg-clip-text text-transparent">
          연락처
        </h1>
        <p className="text-xl text-gray-300 max-w-2xl mx-auto">
          궁금한 점이나 협업 제안이 있으시면 언제든 연락주세요
        </p>
      </div>
      
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-12">
        {/* 연락처 정보 */}
        <ProfileCard variant="contact" />
        
        {/* 연락 폼 */}
        <ContactForm />
      </div>
      
      {/* 소셜 미디어 */}
      <SocialMedia />
    </div>
  );
} 