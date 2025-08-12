'use client'

import { useAuth } from '@/contexts/AuthContext'
import Card from '@/components/ui/Card'
import Button from '@/components/ui/Button'
import { ROUTES } from '@/constants/routes'

export default function HomePage() {
  const { user, loading } = useAuth()

  if (loading) {
    return (
      <div className="flex items-center justify-center">
        <div className="text-lg">로딩중...</div>
      </div>
    )
  }

  return (
    <div className="text-center py-12">
      <h1 className="text-4xl font-bold text-gray-900 mb-4">
        신입사원 친해지기 프로젝트
      </h1>
      <p className="text-xl text-gray-600 mb-8 max-w-2xl mx-auto">
        같은 시기에 입사한 신입사원끼리 서로를 알아가요! 
        간단한 자기소개로 첫 인사를 나눠보세요
      </p>

      {user ? (
        <Card className="max-w-md mx-auto p-6">
          <h3 className="text-lg font-medium text-gray-900 mb-2">
            환영합니다, {user.email}님!
          </h3>
          <p className="text-gray-600 mb-4">
            동기들과 인사를 나누고 서로에 대해 알아가보세요.
          </p>
          <Button href={ROUTES.PROFILES} size="lg">
            동기들 만나러 가기
          </Button>
        </Card>
      ) : (
        <div className="space-y-4">
          <div className="flex justify-center space-x-4">
            <Button href={ROUTES.SIGNUP} size="lg">
              신입사원 등록하기
            </Button>
            <Button href={ROUTES.LOGIN} variant="outline" size="lg">
              로그인
            </Button>
          </div>
          <p className="text-sm text-gray-500">
            이미 가입하셨다면 로그인해주세요
          </p>
        </div>
      )}
    </div>
  )
}
