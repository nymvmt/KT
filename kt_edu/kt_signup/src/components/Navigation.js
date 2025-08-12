'use client'

import { useAuth } from '@/contexts/AuthContext'
import Button from '@/components/ui/Button'
import { ROUTES } from '@/constants/routes'

export default function Navigation() {
  const { user, loading, signOut } = useAuth()

  const handleSignOut = async () => {
    await signOut()
  }

  return (
    <nav className="bg-white shadow-sm border-b">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex items-center">
            <Button href={ROUTES.HOME} variant="link" className="text-xl font-bold text-blue-600">
              신입사원 자기소개
            </Button>
          </div>

          <div className="flex items-center space-x-4">
            {loading ? (
              <div className="text-gray-500">로딩중...</div>
            ) : user ? (
              <>
                <span className="text-gray-700">
                  반가워요, {user.email}님!
                </span>
                <Button href={ROUTES.PROFILES} variant="outline" size="sm">
                  동기들 보기
                </Button>
                <Button onClick={handleSignOut} variant="outline" size="sm">
                  로그아웃
                </Button>
              </>
            ) : (
              <>
                <Button href={ROUTES.LOGIN} variant="outline" size="sm">
                  로그인
                </Button>
                <Button href={ROUTES.SIGNUP} variant="primary" size="sm">
                  신입사원 등록
                </Button>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  )
} 